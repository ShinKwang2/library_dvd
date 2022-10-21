package com.library.jeungsan_dvd.user.web;

import com.library.jeungsan_dvd.user.domain.User;
import com.library.jeungsan_dvd.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/signup")
    public String signUpForm(@ModelAttribute("user") UserSignupDTO userSignupDTO) {
        return "user/signup";
    }

    @PostMapping("/user/signup")
    public String signUp(@ModelAttribute("user") @Validated UserSignupDTO userSignupDTO, BindingResult bindingResult) {

        /**비밀번호 서버에서 체크 */
        if (!checkPassword(userSignupDTO)) {
            bindingResult.reject("passwordFail", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            userSignupDTO.setPassword("");
            userSignupDTO.setPassword("");
            return "user/signup";
        }

        User signupUser = userSignupDTO.toEntity();
        /** 아이디 중복 체크  */
        if (userService.validateDuplicateUser(signupUser)) {
            bindingResult.reject("identityFail", "중복된 아이디가 있습니다. 다른 아이디를 입력해주세요.");
            userSignupDTO.setUserIdentity("");
            userSignupDTO.setPassword("");
            userSignupDTO.setPassword("");
        }

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "user/signup";
        }

        //다 넘어가면
        userService.signup(signupUser);

        return "redirect:/";
    }

    /** 비밀번호 & 비밀번호 확인 체크 */
    private boolean checkPassword(UserSignupDTO userSignupDTO) {
        String password = userSignupDTO.getPassword();
        String passwordCheck = userSignupDTO.getPasswordCheck();
        if (password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }

}
