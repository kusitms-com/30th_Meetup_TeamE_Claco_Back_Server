package com.curateme.claco.authentication.util;

import com.curateme.claco.authentication.domain.JwtMemberDetail;

public interface SecurityContextUtil {

	JwtMemberDetail getContextMemberInfo();

}
