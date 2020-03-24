package io.github.silicondev.siliconmccli;

public class Result {
	public boolean Valid = false;
	public boolean Success = false;
	public boolean Silent = false;
	
	public Result(boolean _valid, boolean _success) {
		Setup(_valid, _success);
	}
	
	public Result(boolean _valid, boolean _success, boolean _silent) {
		Setup(_valid, _success);
		Silent = _silent;
	}
	
	private void Setup(boolean _valid, boolean _success) {
		Valid = _valid;
		Success = _success;
	}
}
