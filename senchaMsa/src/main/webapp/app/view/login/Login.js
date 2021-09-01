Ext.define('Study.view.main.Login', {
    extend : 'Ext.window.Window',
    xtype : 'login',
    controller : 'login',
    viewModel : 'login',
    layout : 'center',
    closable : false,
    maximized : true,
    autoShow : true,
    onEsc : function() {
    	return false;
    },
    items : [
    	{
    		xtype : 'form',
    		layout : {
    			type : 'vbox',
    			align : 'center',
    			pack : 'center'
    		},
    		items : [
    			{
    				xtype : 'textfield',
    				name : 'name'
    			},
    			{
    				xtype : 'textfield',
    				inputType : 'password',
    				name : 'password'
    			},
    			{
    				xtype : 'button',
    				text : 'Login',
    				width : 165,
    				handler : 'loginBtn'
//    				handler : function(btn) {
//    					btn.up("window").close();
//    					Ext.create("Study.view.main.Main");
//    				}
    			}
    		]
    	}
    ]
  

    
});
