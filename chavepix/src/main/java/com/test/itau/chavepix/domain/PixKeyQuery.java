package com.test.itau.chavepix.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PixKeyQuery {

    private UUID id;
    private String keyType;
    private String agencyNumber;
    private String accountNumber;
    private String accountHolderName;
    private LocalDateTime dateCreateStart;
    private LocalDateTime dateCreateEnd;
    private LocalDateTime dateDeleteStart;
    private LocalDateTime dateDeleteEnd;

}
