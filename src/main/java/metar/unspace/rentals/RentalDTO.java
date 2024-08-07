package metar.unspace.rentals;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class RentalDTO {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Getter
    public static class GetRequest {
        private final String id;
        private final LocalDateTime startTime;
        private final Optional<LocalDateTime> endTime;

        public GetRequest(String id, String startTime) {
            this.id = id;
            this.startTime = LocalDateTime.parse(startTime, formatter);
            this.endTime = Optional.empty();
        }

        public GetRequest(String id, String startTime, String endTime) {
            this.id = id;
            this.startTime = LocalDateTime.parse(startTime, formatter);
            this.endTime = Optional.of(LocalDateTime.parse(endTime, formatter));
        }
    }

    @Getter
    public static class PostRequest {

        @NotNull(message = "user id cannot be null")
        private String userId;

        @NotNull(message = "facility id cannot be null")
        private int facilityId;

        @NotNull(message = "start time cannot be null")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$\n", message = "start time must have yyyy-mm-dd HH:mm Format")
        private String startTime;

        @NotNull(message = "end time cannot be null")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$\n", message = "end time must have yyyy-mm-dd HH:mm Format")
        private String endTime;

    }

    public static class Response {

        private final int rentalId;
        private final String facilityName;
        private final String startTime;
        private final String endTime;
        private final String username;

        public Response(Rental rental) {
            this.rentalId = rental.getId();
            this.facilityName = rental.getFacilityName();
            this.startTime = String.format(rental.getStarttime(), formatter);
            this.endTime = String.format(rental.getEndtime(), formatter);
            this.username = rental.getUsername();
        }
    }
}
