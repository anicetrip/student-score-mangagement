package com.chd.score.demo.service;

import com.chd.score.demo.webbean.YysbLogin;

public interface SpeechService {
    YysbLogin login(String userID, String password);
}
