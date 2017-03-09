public interface Comparator {
  public boolean isEqualTo(Object item1, Object item2);
  public boolean isGreaterThan(Object item1, Object item2);
  public boolean isGreaterThanOrEqualTo(Object item1, Object item2);
  public boolean isLessThan(Object item1, Object item2);
  public boolean isLessThanOrEqualTo(Object item1, Object item2);
}
