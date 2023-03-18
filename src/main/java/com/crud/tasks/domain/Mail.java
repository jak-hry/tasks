package com.crud.tasks.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Mail {

    private final String mailTo;
    private final String subject;
    private final String message;
    private final String[] toCc;
}
