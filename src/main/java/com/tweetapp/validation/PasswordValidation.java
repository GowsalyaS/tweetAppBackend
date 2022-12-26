package com.tweetapp.validation;

import com.tweetapp.model.UserProfile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<PasswordValidationAnnotation, UserProfile> {

    /**
     *
     * @param constraintAnnotation
     * @return String
     */
    @Override
    public void initialize(PasswordValidationAnnotation constraintAnnotation) {
        // initialize.
    }

    @Override
    public boolean isValid(UserProfile userProfile, ConstraintValidatorContext constraintValidatorContext) {
      return  userProfile.getPassword().equals(userProfile.getConfirmpassword());
    }


}
