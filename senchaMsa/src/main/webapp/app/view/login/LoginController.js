Ext.define('Study.view.main.LoginController', {
    extend : 'Ext.app.ViewController',
    alias : 'controller.login',
    loginBtn : function(btn) {
   
    	var values = btn.up("form").getForm().getValues();
    	
    	Ext.Ajax.request({
    	
    		url : '/loginInfo',
    		method : 'POST',
    		params : values,
    		success : function(response) {
    			var api = Ext.decode(response.responseText);
    			console.log(api);
    			
    			if(api['code'] == 200) {
    				btn.up("window").close();
    				Ext.widget("main");
    			} else {
    				alert(api['msg']);
    				return;
    			}
    		}
    	});
//    	btn.up("window").close();
//		Ext.widget("main");
    }
});