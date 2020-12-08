package com.dh.demo.Projection;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {
    @Value( "#{target.userName + ' - > ' + target.permissionLevel}" )
    String getUserNameAndPermissionLevel();

    String getEmail();
}
