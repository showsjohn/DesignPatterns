public class Deadline extends Event implements Completable
{
    boolean complete;

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
