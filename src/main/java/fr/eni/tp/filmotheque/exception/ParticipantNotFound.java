package fr.eni.tp.filmotheque.exception;

public class ParticipantNotFound extends RuntimeException   {
    public ParticipantNotFound(String message) {
        super(message);
    }
}
