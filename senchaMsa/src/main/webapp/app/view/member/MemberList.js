Ext.define('Study.view.member.MemberList', {
	extend : 'Ext.grid.Panel',
	listeners : {
		boxready : 'onLoadData'
	},
	xtype : 'memberList',
    controller : 'memberList',
    viewModel : 'memberList',
	title : '회원 목록',
	columnLines : true,
	plugins : 'cellediting',
	margin : 10,
	tbar : [
		{
			xtype : 'combo',
			editable : false,
			displayField : 'key',
			valueField : 'value',
			value : 'name',
			name : 'searchCode',
			store : {
				fields : ['key', 'value'],
				data : [
					{
						key : '아이디',
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
			text : '아이디',
			flex : 1,
			dataIndex : 'name',
			editor : {
				xtype : 'textfield'
			}
		},
		{
			text : '비밀번호',
			flex : 1,
			dataIndex : 'password',
			editor : {
				xtype : 'textfield'
			}
		},
		{
			text : '주소',
			flex : 1,
			dataIndex : 'address',
			editor : {
				xtype : 'textfield'
			}
		},
		{
			text : '연락처',
			flex : 1,
			dataIndex : 'phone',
			editor : {
				xtype : 'textfield'
			}
		},
		{
			text : '우편번호',
			flex : 1,
			dataIndex : 'zipcode',
			editor : {
				xtype : 'textfield'
			}
		}
		
	],
	bind : {
		store : '{memberList}'
	},
	bbar : {
		xtype : 'pagingtoolbar',
//		plugins : 'ux-slidingpager',
		plugins : 'ux-progressbarpager',
		displayInfo : true
	}
	
});