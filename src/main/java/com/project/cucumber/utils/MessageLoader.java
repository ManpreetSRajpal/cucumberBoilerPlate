package com.project.cucumber.utils;

public class MessageLoader {
    private static final String ENV_USER = "messages/";
    private static Messages messages;

    public static Messages getMessages() {
        return messages;
    }

    public static void setMessages(String language) {
        messages = (Messages) new YAMLLoader().getObject(ENV_USER + language + ".yml", Messages.class);
    }

}