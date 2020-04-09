package com.company.microuserservice.controller;

import com.company.microuserservice.domain.Limitation;
import com.company.microuserservice.domain.User;
import com.company.microuserservice.repository.LimitationRepository;
import com.company.microuserservice.repository.UserRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PermissionController {

    final LimitationRepository limitationRepository;
    final UserRepository userRepository;

    @GetMapping
    public boolean isPermittedForUser(String userId, String type) {
        Limitation limitation = limitationRepository.findLimitationByType(type);
        if (limitation == null) {
            return true;

        } else {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (!optionalUser.isPresent()) {
                return false;
            }
            User user = optionalUser.get();

            return countAge(user.getDateOfBirth()) > limitation.getMinAge()
                    && !(user.getCriminal() && limitation.getRequireNoCriminal())
                    && !(user.getMentalIllness() && limitation.getRequireNoIllness());
        }
    }

    private int countAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
