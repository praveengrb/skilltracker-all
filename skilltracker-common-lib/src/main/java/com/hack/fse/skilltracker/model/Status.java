package com.hack.fse.skilltracker.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Status {
    @Getter @Setter
    private boolean status;
    @Getter @Setter
    private boolean message;
}
