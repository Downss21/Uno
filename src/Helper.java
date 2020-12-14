import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Helper {
	public static final Set<String> CARDS = new HashSet<String>(Arrays.asList(
			"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Skip","Reverse","+2","Wild","+4"
			));
	public static final Set<String> ACTION_CARDS = new HashSet<String>(Arrays.asList(
			"Skip","Reverse","+2","Wild","+4"
			));
	public static final Set<String> NUMBER_CARDS =  new HashSet<String>(Arrays.asList(
			"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"
			));
	public static final Set<String> WILD_CARDS =  new HashSet<String>(Arrays.asList(
			"Wild", "+4"
			));
	public static final String[] UNIQUE_CARDS = {
			"Zero","Wild","+4"
	};
	public static final String[] COMMON_CARDS = {
			"One","Two","Three","Four","Five","Six","Seven","Eight","Nine", "Skip","Reverse","+2"
		};
	public static final String[] COLORS = {
			"\u001b[38;2;255;0;0mRed","\u001b[38;2;255;255;0mYellow","\u001b[38;2;0;255;0mGreen","\u001b[38;2;0;0;255mBlue"
	};
}