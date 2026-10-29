package com.github.ndytar_s.capacity_test_project.controller;


import com.github.ndytar.capacity.agentic.AgentContext;
import com.github.ndytar.capacity.annotation.InjectAgentContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Le contrôleur ne lit plus jamais request.getAttribute(...), n'appelle
 * plus ToolVisibilityFilter lui-même : tout est résolu en amont par
 * AgentContextArgumentResolver et injecté ici comme un paramètre normal.
 */
@Slf4j
@RestController
@RequestMapping("/agent")
public class AgentController {
    private final ChatClient agentManager;
    public AgentController(ChatClient agentManager ) {
        this.agentManager = agentManager;

    }
    /***
     * *****************  NB  ***************
     * Dans le cadre de DPoP, la premiere capacité doit etre signer par le preuve
     */
    @PostMapping("/chat")
    public String chat(@InjectAgentContext AgentContext ctx, @RequestBody String userInput) {

        LoggerFactory.getLogger(AgentController.class).info("Tools visibles : {}",
               ctx.toolsVisibles().stream().map(t -> t.getToolDefinition().name()).toList());
        return agentManager.prompt()
                .user(userInput)
                .tools(ctx.toolsVisibles())
                .toolContext(ctx.toolContext())
                .call()
                .content();
    }
}

