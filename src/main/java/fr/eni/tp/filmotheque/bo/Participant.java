package fr.eni.tp.filmotheque.bo;

public class Participant extends Personne{

    private static final long serialVersionUID = 1L;

    public Participant(){

    }

    public Participant(int id, String nom, String prenom){
        super(id, nom,prenom);
    }

    public Participant(String nom, String prenom){
        super(nom,prenom);
    }
}
