package com.uphar.bussiness.domain.Utils.networkException;

import java.io.IOException;

public class AccessDeniedException extends IOException {

    @Override
    public String getMessage() {
        return "Access denied";
    }

}