Ext.define('Study.view.window.SelectDelivery', {
	extend : 'Ext.window.Window',
	listeners : {
    	boxready : 'onLoadData'
    },
	xtype : 'selectDelivery',
    controller : 'selectDelivery',
    viewModel: 'selectDelivery',
	width: 610,
	height: 260,
	title: '배송정보 조회',
	modal: true,
	autoShow: true,
	layout: {
		type: 'vbox',
		align: 'stretch'
	},
	bodyPadding: 10,
	items: [
		{
			xtype: 'textfield',
	        fieldLabel: '받는사람',
	        bind : {
	        	value : '{deliveryNm}'
	        }
	        
		},
		{
			xtype: 'textfield',
	        fieldLabel: '주소',
	        bind : {
	        	value : '{deliveryAddress}'
	        }
		},
		{
			xtype: 'textfield',
	        fieldLabel: '배송상태',
	        bind : {
	        	value : '{deliveryStatus}'
	        }
		}
	],
	buttons : [
		{
			text: '닫기',
	        handler: function(btn){
	        	btn.up("window").close();
	        }
		}
	],
	store : '{deliveryStore}'
});