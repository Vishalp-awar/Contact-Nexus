package com.ContactNexus.Helper;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
    private String content;
    @Builder.Default
    private AlertType type = AlertType.green;

}