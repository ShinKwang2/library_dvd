package com.library.jeungsan_dvd;

import com.library.jeungsan_dvd.item.service.DvdService;
import com.library.jeungsan_dvd.item.web.PopularDvdResponseDTO;
import com.library.jeungsan_dvd.user.domain.User;
import com.library.jeungsan_dvd.user.infra.SessionConst;
import com.library.jeungsan_dvd.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserService userService;

    private final DvdService dvdService;

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {
        log.info("homeController");

        List<PopularDvdResponseDTO> popularDvdList = dvdService.popularDvdList();
        model.addAttribute("popularDvdList", popularDvdList);

        for (PopularDvdResponseDTO popularDvdResponseDTo : popularDvdList) {
            log.info("dvdName={}", popularDvdResponseDTo.getTitle());
        }

        //세션이 없으면 home 으로
        HttpSession session = request.getSession();
        if (session == null) {
            log.info("noSession");
            return "index";
        }

        Long loginUserId = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        //세션에 loginMember 가 없으면 home 으로
        if (loginUserId == null) {
            log.info("noUser");
            return "index";
        }

        //만약 loginMember 가 있다면, username만 loginHome View로 넘겨주기
        User loginUser = userService.findOne(loginUserId);
        model.addAttribute("username", loginUser.getUserName());
        model.addAttribute("userRole", loginUser.getUserName());

        return "index";
    }
}
