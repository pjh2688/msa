Ext.define('Study.view.main.Main', {
	extend : 'Ext.container.Viewport',
	xtype : 'main',
    controller : 'main',
	layout : 'border',
	items : [
		{
			xtype : 'panel',
			region : 'north',
		//	title : 'Market Admin'
			title : '',
			header : false,
			items : [
				{
					xtype : 'toolbar',
					cls : 'top',
					items : [
						{
							xtype : 'label',
							html : '<h1>Market Admin</h1>'
						}, '->',
						{
							xtype : 'button',
							scale : 'large',
							ui : 'materialbtn',
							text : 'xxx님',
							menu : [
								{
									text : '비밀번호 변경',
									handler : 'updatePasswordBtn'
									
								},
								{
									text : '로그 아웃',
									handler : 'logoutBtn'

								}
							]
						}
					]
				}
			]
		},
		{
			xtype : 'panel',
			region : 'west',
			width : 278,
			layout : 'fit',
			items : [
				{
					xtype : 'treelist',
					ui : 'menulist',
					listeners : {
						selectionchange : 'menuChange'
					},
					store : {
						root : {
							expanded : true,
							children : [
								{
									text : '상품 관리',
									iconCls : 'fab fa-product-hunt',
									expanded : true,
									selectable : false,
									children : [
										{
											text : '상품 목록',
											page : 'productList',  // 2. xtype에 해당 이름이 있는 js랑 매칭
											leaf : true
										}
									]
								},
								{
									text : '주문 관리',
									iconCls : 'fab fa-first-order',
									expanded : true,
									selectable : false,
									children : [
										{
											text : '주문 목록',
											page : 'orderList',
											leaf : true
										}
									]
								},
								{
									text : '회원 관리',
									iconCls : 'far fa-user',
									expanded : true,
									selectable : false,
									children : [
										{
											text : '회원 목록',
											page : 'memberList',
											leaf : true
										}
									]
								}
							]
						}
					}
				}
			]
		},
		{
			xtype : 'container',
			region : 'center',
			flex : 1,
			layout : 'fit',
			items : [
				{
					xtype : 'panel',
					html : "<h2>Main DashBoard</h2>"
				}
			]
		}
	]
	
});