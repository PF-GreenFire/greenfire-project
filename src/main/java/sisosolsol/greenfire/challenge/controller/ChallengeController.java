package sisosolsol.greenfire.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisosolsol.greenfire.challenge.model.dto.ChallengeCreateDTO;
import sisosolsol.greenfire.challenge.service.ChallengeService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<Void> createChallenge(@RequestBody ChallengeCreateDTO challenge) {
        Integer challengeCode = challengeService.registChallenge(challenge);
        return ResponseEntity.created(URI.create("/challenge/" + challengeCode)).build();
    }
}
