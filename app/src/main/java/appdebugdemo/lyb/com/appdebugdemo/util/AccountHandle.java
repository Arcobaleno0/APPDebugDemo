/*
 * Copyright 2011 - AndroidQuery.com (tinyeeliu@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package appdebugdemo.lyb.com.appdebugdemo.util;

import android.content.Context;

import org.apache.http.HttpRequest;

import java.net.HttpURLConnection;
import java.util.LinkedHashSet;

import appdebugdemo.lyb.com.appdebugdemo.callback.AbstractAjaxCallback;
import appdebugdemo.lyb.com.appdebugdemo.model.AjaxStatus;

public abstract class AccountHandle {

	
	private LinkedHashSet<AbstractAjaxCallback<?, ?>> callbacks;
	
	public synchronized void auth(AbstractAjaxCallback<?, ?> cb){
		
		if(callbacks == null){
			callbacks = new LinkedHashSet<AbstractAjaxCallback<?,?>>();
			callbacks.add(cb);
			auth();
		}else{
			callbacks.add(cb);
		}
				
	}
	
	
	public abstract boolean authenticated();

	protected synchronized void success(Context context){
		
		if(callbacks != null){
			
			for(AbstractAjaxCallback<?, ?> cb: callbacks){
				cb.async(context);
			}
			
			callbacks = null;
		}
		
	}
	
	protected synchronized void failure(Context context, int code, String message){		
		
		if(callbacks != null){
			
			for(AbstractAjaxCallback<?, ?> cb: callbacks){
				cb.failure(code, message);
			}
			
			callbacks = null;
		}
		
	}
	
	
	protected abstract void auth();
	
	public abstract boolean expired(AbstractAjaxCallback<?, ?> cb, AjaxStatus status);
	
	public abstract boolean reauth(AbstractAjaxCallback<?, ?> cb);
	
	public void applyToken(AbstractAjaxCallback<?, ?> cb, HttpRequest request){		
	}
	
	public void applyToken(AbstractAjaxCallback<?, ?> cb, HttpURLConnection conn){
	}
	
	public String getNetworkUrl(String url){
		return url;
	}
	
	public String getCacheUrl(String url){
		return url;
	}
	
	public void unauth(){		
	}
	
}
