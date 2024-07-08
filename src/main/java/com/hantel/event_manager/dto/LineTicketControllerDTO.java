package com.hantel.event_manager.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LineTicketControllerDTO {
    private Long lineId;
    private int lineOrdinalNumber;
}
