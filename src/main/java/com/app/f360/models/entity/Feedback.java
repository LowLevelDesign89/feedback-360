package com.app.f360.models.entity;

import com.app.f360.models.enums.FeedbackStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("feedback")
@Data
public class Feedback {
    @Id
    private String id;
    private String requestedBy;
    private String requestedFor;
    private String requestedTo;
    private FeedbackStatus status;

    private String detailedFeedback;
}
