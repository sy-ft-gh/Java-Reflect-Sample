import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Main {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = RefSample.class;
		// 引数1つのコンストラクタを取得し、インスタンスを生成する
		Constructor<? extends Object> cons = clazz.getConstructor(int.class);
		RefSample rs = (RefSample) cons.newInstance(256);
		// MIN_TIMESフィールド(public)に関するFieldを取得して読み書き
		Field f = clazz.getField("MIN_TIMES");
		System.out.println(f.get(rs)); // rsのMIN_TIMESを取得
		try {
			// timesフィールド 
			f.set(rs, 2);                  // rsのMIN_TIMESに代入
		} catch (java.lang.IllegalAccessException ex) {
			// final指定なのでアクセスエラーが発生する
			System.out.println(ex.getClass() + ":" +  ex.getMessage());
		}
		// finalフィールドをアクセス可能に変更
		f.setAccessible(true);
		f.set(rs, 2);                  // rsのfinal MIN_TIMESに代入
		System.out.println(f.get(rs)); // rsのMIN_TIMESを取得

		// privateのtimesフィールドを取得。
		f = clazz.getDeclaredField("times");
		// privateフィールドをアクセス可能に変更
		f.setAccessible(true);
		// フィールド値の書き込み
		f.set(rs, 2);                  // rsのrsのprivate timesに代入
		// フィールド値の取得
		System.out.println(f.get(rs)); // rsのprivate timesを取得

		// 引数2つのhelloメソッドを取得して呼び出す
		Method m = clazz.getMethod("hello", String.class, int.class);
		m.invoke(rs, "reflection!", 128);
		// クラスやメソッドへの修飾（publicやfinalの有無）を調べる
		boolean pubc = Modifier.isPublic(clazz.getModifiers());
		System.out.println("IsPublic:" + pubc);
		boolean finm = Modifier.isFinal(m.getModifiers());
		System.out.println("isFinal:" + finm);
		// privateのcheckTimeInvalidメソッドにアクセスする
		m = clazz.getDeclaredMethod("checkTimeInvalid", int.class);
		// privateメソッドをアクセス可能に変更
		m.setAccessible(true);
		// 可視可したprivateメソッドの実行
		m.invoke(rs, 1);

	}
}
