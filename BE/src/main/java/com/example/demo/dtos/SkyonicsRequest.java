package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SkyonicsRequest extends CommandRequest{
    private String APIKey;
    private String serialNumber;
    private int mode;


    public SkyonicsRequest(String APIKey,String serialNumber,String command,int mode) {
        super(command);
        this.APIKey = APIKey;
        this.serialNumber = serialNumber;
        this.mode = mode;
    }
}
