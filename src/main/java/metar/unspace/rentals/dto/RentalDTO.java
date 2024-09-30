package metar.unspace.rentals.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class RentalDTO {

    @Getter
    public static class GetRequest {
        private final String id;
        private final String startTime;
        private final String endTime;

        public GetRequest(String id, String startTime, String endTime) {
            this.id = id;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }


    @Getter
    public static class PostRequest {

        @NotNull(message = "user id cannot be null")
        private String userId;

        @NotNull(message = "user name cannot be null")
        private String userName;

        @NotNull(message = "facility name cannot be null")
        private String facilityName;

        @NotNull(message = "start time cannot be null")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2})?$",
                message = "start time must have ISO 8601 format (yyyy-MM-dd'T'HH:mm[:ss])")
        private String start;

        @NotNull(message = "end time cannot be null")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2})?$",
                message = "end time must have ISO 8601 format (yyyy-MM-dd'T'HH:mm[:ss])")
        private String end;

    }

    @Getter
    public static class Response {

        private final int rentalId;
        private final String facilityName;
        private final String startTime;
        private final String endTime;
        private final String userId;
        private final String username;

        public Response(Rental rental) {
            this.rentalId = rental.getId();
            this.facilityName = rental.getFacilityName();
            this.startTime = String.format(String.valueOf(rental.getStart()), ISO_DATE_TIME);
            this.endTime = String.format(String.valueOf(rental.getEnd()), ISO_DATE_TIME);
            this.userId = rental.getUserId();
            this.username = rental.getUsername();
        }
    }
}
