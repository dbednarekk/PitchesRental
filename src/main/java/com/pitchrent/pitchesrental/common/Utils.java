package com.pitchrent.pitchesrental.common;

import com.pitchrent.pitchesrental.exceptions.AccountManagerException;
import com.pitchrent.pitchesrental.exceptions.BaseException;

import static com.pitchrent.pitchesrental.common.I18n.OPTIMISTIC_LOCK_EXCEPTION;

public class Utils {

    public static void checkForOptimisticLock(Long v1, Long v2) throws BaseException {
        if (!v1.equals(v2)) throw new AccountManagerException(OPTIMISTIC_LOCK_EXCEPTION);
    }
}
