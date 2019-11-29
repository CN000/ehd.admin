package com.ehd.admin.common;


/**
 * AjaxJson
 * @author 16616
 *
 */
public class AjaxJson
{
	
	private String msg;
	
	private boolean success;
	
	private Object data;

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	@Override
	public String toString() {
		return "AjaxJson [msg=" + msg + ", success=" + success + ", data=" + data + "]";
	}

	
}
