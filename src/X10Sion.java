public interface X10Sion {
    public final String X10SION_VERSION = "A.1";
    public final int X10SION_COMPAT = 1;
    public String X10SionVersion();
    public int X10SionCompatibility();
    public String getVersion();
    public int getBuild();
    public String getName();
    public String getVendor();
    public int run(Object[] dynamicParameters);
}
