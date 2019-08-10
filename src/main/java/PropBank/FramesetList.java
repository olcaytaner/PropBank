package PropBank;

import java.io.*;
import java.util.*;

public class FramesetList {

    private ArrayList<Frameset> frames;

    /**
     * A constructor of {@link FramesetList} class which reads all frameset files inside the files.txt file. For each
     * filename inside that file, the constructor creates a Frameset and puts in inside the frames {@link ArrayList}.
     */
    public FramesetList(){
        frames = new ArrayList<Frameset>();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("files.txt");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()){
            frames.add(new Frameset(classLoader.getResourceAsStream(scanner.next())));
        }
    }

    /**
     * readFromXML method searches the {@link Frameset} with a given synSetId if there is a {@link Frameset} with the given synSet id,
     * returns the arguments of that {@link Frameset} as an {@link HashMap}.
     *
     * @param synSetId  Id of the searched {@link Frameset}
     * @return an {@link HashMap} containing the arguments of the searched {@link Frameset}
     */
    public Map<ArgumentType, String> readFromXML(String synSetId) {
        Map<ArgumentType, String> frameset = new HashMap<ArgumentType, String>();
        for (Frameset f:frames){
            if (f.getId().equals(synSetId)){
                for (int i = 0; i < f.getFramesetArguments().size(); i++){
                    frameset.put(ArgumentType.valueOf(f.getFramesetArguments().get(i).getArgumentType()), f.getFramesetArguments().get(i).getDefinition());
                }
            }
        }
        return frameset;
    }

    /**
     * frameExists method checks if there is a {@link Frameset} with the given synSet id.
     *
     * @param synSetId  Id of the searched {@link Frameset}
     * @return true if the {@link Frameset} with the given id exists, false otherwise.
     */
    public boolean frameExists(String synSetId){
        for (Frameset f:frames){
            if (f.getId().equals(synSetId)){
                return true;
            }
        }
        return false;
    }

    /**
     * getFrameSet method returns the {@link Frameset} with the given synSet id.
     *
     * @param synSetId  Id of the searched {@link Frameset}
     * @return {@link Frameset} which has the given id.
     */
    public Frameset getFrameSet(String synSetId){
        for (Frameset f:frames){
            if (f.getId().equals(synSetId)){
                return f;
            }
        }
        return null;
    }

    /**
     * The addFrameset method takes a {@link Frameset} as input and adds it to the frames {@link ArrayList}.
     *
     * @param frameset  Frameset to be added
     */
    public void addFrameset(Frameset frameset){
        frames.add(frameset);
    }

    /**
     * The getFrameSet method returns the frameset at the given index.
     *
     * @param index  Index of the frameset
     * @return {@link Frameset} at the given index.
     */
    public Frameset getFrameSet(int index){
        return frames.get(index);
    }

    /**
     * The size method returns the size of the frames {@link ArrayList}.
     *
     * @return the size of the frames {@link ArrayList}.
     */
    public int size(){
        return frames.size();
    }

}
