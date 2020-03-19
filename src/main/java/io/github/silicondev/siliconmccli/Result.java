package io.github.silicondev.siliconmccli;

public class Result {
	public boolean Valid = false;
	public boolean Success = false;
	
	public Result(boolean _valid, boolean _success) {
		Valid = _valid;
		Success = _success;
	}
}
