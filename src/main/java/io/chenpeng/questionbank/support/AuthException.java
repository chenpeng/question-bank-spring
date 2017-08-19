package io.chenpeng.questionbank.support;

import io.chenpeng.questionbank.enumeration.AuthEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthException extends Exception {
    private AuthEnum code;

    private String msg;
}
