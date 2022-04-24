package com.team1.betterhip.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BetterHipCommand {
	public void execute(HttpServletRequest request,Model model);
}
