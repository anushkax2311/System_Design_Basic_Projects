package Music_Player_Project.managers;

import Music_Player_Project.enums.PlayStrategyType;
import Music_Player_Project.strategies.CustomQueueStrategy;
import Music_Player_Project.strategies.PlayStrategy;
import Music_Player_Project.strategies.RandomPlayStrategy;
import Music_Player_Project.strategies.SequentialPlayStrategy;

public class StrategyManager {
    private static StrategyManager instance= null;
    private SequentialPlayStrategy sequentialStrategy;

    private RandomPlayStrategy randomStrategy;
    private CustomQueueStrategy customQueueStrategy;

    private StrategyManager(){
        sequentialStrategy = new SequentialPlayStrategy();
        randomStrategy = new RandomPlayStrategy();
        customQueueStrategy = new CustomQueueStrategy();
    }

    public static synchronized StrategyManager getInstance(){
        if (instance==null) {
            instance = new StrategyManager();
        }
        return instance;
    }
public PlayStrategy getStrategy(PlayStrategyType type){
    if (type==PlayStrategyType.SEQUENTIAL) {
        return sequentialStrategy;
    }else if (type==PlayStrategyType.RANDOM) {
        return randomStrategy;
    }else{
        return customQueueStrategy;
    }
}

}
