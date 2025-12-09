package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("participants")
public class ParticipantServiceImpl implements ParticipantService {

    ParticipantRepository participant;

    public ParticipantServiceImpl(ParticipantRepository participant) {
        this.participant = participant;
    }
    @Override
    public List<Participant> consulterParticipants() {
        return participant.findAllParticipants();
    }

    @Override
    public Participant consulterParticipantParId(int id) {
        return participant.findParticipantById(id);
    }
}
