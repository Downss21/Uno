import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.script.SimpleBindings;

public class Helper {
	public static final Set<String> CARDS_SET = new HashSet<String>(Arrays.asList(
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
	public static final String[] CARDS_ARRAY = {
			"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Skip","Reverse","+2","Wild","+4"
	};
	public static final String[] UNIQUE_CARDS = {
			"Zero","Wild","+4"
	};
	public static final String[] COMMON_CARDS = {
			"One","Two","Three","Four","Five","Six","Seven","Eight","Nine", "Skip","Reverse","+2"
		};
	public static final String[] COLORS = {
			"\u001b[38;2;255;0;0mRed","\u001b[38;2;255;255;0mYellow","\u001b[38;2;0;255;0mGreen","\u001b[38;2;0;127;255mBlue"
	};
	public static final Map<String, Object> POINTS = createBinding(CARDS_ARRAY, new Integer[] {0,1,2,3,4,5,6,7,8,9,20,20,20,50,50});
	
	private static Map<String, Object> createBinding(String[] strings, Object[] objects) {
		int length = strings.length;
		Map<String, Object> map = new SimpleBindings();
		for (int i = 0; i < length; i++)
		{
			map.put(strings[i], objects[i]);
		}
		return map;
	}
}