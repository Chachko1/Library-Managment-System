package org.librarymanagementsystem.schedule;

import org.librarymanagementsystem.services.MemberService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final MemberService memberService;

    public ScheduledTasks(MemberService memberService) {
        this.memberService = memberService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetLoginStatus(){
        memberService.resetLoginStatus();
    }
}
