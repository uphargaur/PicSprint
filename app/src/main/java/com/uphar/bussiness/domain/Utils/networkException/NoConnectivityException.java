package com.uphar.bussiness.domain.Utils.networkException;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity";
    }

}
