Ext.define('Study.view.order.OrderList', {
	extend : 'Ext.grid.Panel',
	listeners : {
		boxready : 'onLoadData'
	},
	xtype : 'orderList',
    controller : 'orderList',
    viewModel : 'orderList',
	title : '주문 목록',
	columnLines : true,
	margin : 10,
	tbar : [
		{
			xtype : 'combo',
	    	editable : false,
	    	displayField : 'key',
			valueField : 'value',
			queryMode : 'local',
			value : 'name',
	    	store : {
	    		fields : ['key','value'],
	    		data : [
	    			{
	    				key : '주문자명',
	    				value : 'name'
	    			}
	    		]
	    	}
		},
		{
			xtype : 'textfield',
			name : 'searchValue',
			emptyText : '검색어를 입력하세요.',
			bind : {
				value : '{searchValue}'
			}
		},
		{
			xtype : 'button',
			text : '검색',
			handler : 'searchBtn'
		}
	],
	columns : [
		{
			xtype : 'rownumberer'
		},
		{
	    	text : '주문자명',
	    	flex : 1,
	    	dataIndex : 'member',
	    	renderer : function(value, meta, record) { 
	    		return value.name;
	    	}
	    },
	    {
	    	text : '주문일',
	    	flex : 1,
	    	dataIndex : 'orderDate',
	    	renderer : function(value) {
	    		if(value != undefined && value != null && value != "") {  // null check
	    			return Ext.util.Format.date(new Date(value), "Y-m-d H:i:s");
	    		}
	    		return value;
	    	}
	    },
	    {
	    	text : '주문상태',
	    	flex : 1,
	    	dataIndex : 'status',
	    	renderer : function(value) {
	    		if(value != undefined && value != null && value != "") {
	    			if(value == "ORDER") {
	    				value = "주문완료";
	    			} else if(value == "CANCEL") {
	    				value ="주문취소";
	    			}
	    		}
	    		return value;
	    	}
	    },
	    {
	    	text : '제품명',
	    	flex : 1,
	    	dataIndex : 'orderProducts',
	    	renderer : function(value, meta, record) { 
	    		   
	    		for(var i=0; i < value.length; i++) {
	    			var op = value[i];
	    			
	    			return op.product.name;
	    		}

	    	}
	    },
	    {
	    	text : '수량',
	    	flex : 1,
	    	dataIndex : 'orderProducts',
	    	renderer : function(value, meta, record) { 
	   
	    		for(var i=0; i < value.length; i++) {
	    			var op = value[i];
	    			
	    			return op.count;
	    		}

	    	}
	    },
	    {
	    	text : '금액',
	    	flex : 1,
	    	dataIndex : 'totalPrice',
	    	renderer : function(value) {
	    		return Ext.util.Format.number(value, "0,000");
	    	}
	    },
		{
			xtype : 'widgetcolumn',
			widget : {
				xtype : 'button',
				text : '배송정보',
				handler : 'deliveryInfoBtn'
			}
		}
	],
	bind : {
		store : '{orderList}'
	},
	bbar : {
		xtype : 'pagingtoolbar',
		plugins : 'ux-progressbarpager',
		displayInfo : true
	}
	
});