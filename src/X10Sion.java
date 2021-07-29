public interface X10Sion {
    public final String X10SION_VERSION = "A.1";
    public final int X10SION_COMPAT = 1;
    public void getVersion();
    public void getName();
    public void getVendor();
    public int run(Object[] dynamicParameters);
}
