public enum Months
{
    January, February, March, April, May, June, July, August, September, October, November, December;

    private static Months[] vals = values();

    public Months next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public Months previous() {
        System.out.println((this.ordinal() - 1) % vals.length);
        return vals[((this.ordinal() - 1) + vals.length) % vals.length];
    }
}
