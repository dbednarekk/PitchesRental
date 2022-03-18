package com.pitchrent.pitchesrental.common;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.pitchrent.pitchesrental.exceptions.ETagException;

import java.text.ParseException;

public  class EtagIntegrityVerifier {
    public static String calculateEntitySignature(SignableEntity entity) throws ETagException {
        try {
            JWSSigner signer = new MACSigner("8YuS04LvRqjpnGnet02bvcdoLIubmcXEt597Gj1rU6bW2MXHvQM90jNnascqF71jsmbp-co91xqE1hie-xKz68BwqAfukX8pGCpXtlzXxrXF_fz46kTcC1HsbvwDzLpxaoAoRKAtEt0onytN4wflPcNvzWjZvAYVcfhb6ydUofU");

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(entity.getSignablePayload()));
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new ETagException("Request's ETag header does not match provided data", e);
        }
    }


    public static boolean validateEntitySignature(String tag) {
        try {
            JWSObject jwsObject = JWSObject.parse(tag);
            JWSVerifier verifier = new MACVerifier("8YuS04LvRqjpnGnet02bvcdoLIubmcXEt597Gj1rU6bW2MXHvQM90jNnascqF71jsmbp-co91xqE1hie-xKz68BwqAfukX8pGCpXtlzXxrXF_fz46kTcC1HsbvwDzLpxaoAoRKAtEt0onytN4wflPcNvzWjZvAYVcfhb6ydUofU");
            return jwsObject.verify(verifier);

        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean verifyEntityIntegrity(SignableEntity entity, String tag) {
        try {
            final String header = JWSObject.parse(tag).getPayload().toString();
            final String signableEntityPayload = entity.getSignablePayload();
            return validateEntitySignature(tag) && signableEntityPayload.equals(header);

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
