package com.auth.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDetailsResponse {

	
	private String Message;
    private String Status;
    private List<PostOffice> PostOffice;

    @Data
    public static class PostOffice {
        private String Name;
        private String Description;
        private String BranchType;
        private String DeliveryStatus;
        private String Circle;
        private String District;
        private String Division;
        private String Region;
        private String State;
        private String Country;
    }
}
