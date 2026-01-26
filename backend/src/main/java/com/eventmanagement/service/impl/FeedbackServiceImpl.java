package com.eventmanagement.service.impl;
import com.eventmanagement.dto.FeedbackRequestDTO;
import com.eventmanagement.dto.FeedbackResponseDTO;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Feedback;
import com.eventmanagement.entity.User;
import com.eventmanagement.exception.ResourceNotFoundException;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.FeedbackRepository;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public FeedbackResponseDTO addFeedback(
            FeedbackRequestDTO request,
            String userEmail
    ) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setEvent(event);
        feedback.setComment(request.getComment());

        Feedback saved = feedbackRepository.save(feedback);

        return new FeedbackResponseDTO(
                saved.getId(),
                event.getTitle(),
                user.getEmail(),
                saved.getComment()
        );
    }
}
