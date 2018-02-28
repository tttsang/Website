/**
 * 版本号: v0.1.0
 * 
 * 版本更新说明: 更改了修改密码接口
 * 
 * 1. GET 表示 查询, POST 表示 增加, PUT 表示 修改, DELETE 表示 删除
 *    其中 POST, PUT, DELETE请求都做了拦截处理, 只有登录后才能访问
 * 
 * 2. 文件上传不能PUT, 所以更新的接口更改为POST, 路径由原来的 /{id} 改为 /update/{id}
 * 
 * 3. 如果不提供后端返回的接口,则默认成功后返回的是
 * {
 *      'code': 0,
 *      'message': '成功'
 * }
 */

/**
 * 1.登录模块 ( 测试账号 id:admin, password:admin  )
 * 测试页面: http://bingexxx.net/test/login.html
 */

//登录      Post    url:http://bingexxx.net/api/login
{
    'id': 'xxx',
        'password': 'xxx'
}

//退出登录    Get    url:http://bingexxx.net/api/logout

//修改密码      Post    url:http://bingexxx.net/api/changePassword
{
    'oldPassword': 'xxx',
    'newPassword': 'xxx',
    'surePassword': 'xxx'
}

/**
 * 2.教授风采模块
 * 测试页面: http://bingexxx.net/test/indexProfessor.html
 */

//图片预览    Post(单文件上传)    http://bingexxx.net/api/previewPic
{
    'file': xxx    //文件流格式
}

//添加教授    Post(单文件上传)  http://bingexxx.net/api/indexProfessor/
{
    'name': '彭小红',
        'file': xxx    //文件流格式
}

//删除教授    DELETE   http://bingexxx.net/api/indexProfessor/{id}

//更改教授    PUT(单文件上传)   http://bingexxx.net/api/indexProfessor/update/{id}
{
    'name': '彭小红',
        'file': xxx
}

//获取所有教授列表    GET   http://bingexxx.net/api/indexProfessor/
{   //后端返回
    'code': 1,
        'message': '成功'
    'data': [
        {
            'id': 1,
            'pictureurl': 'xxx',
            'name': 'xxx'
        },
        {
            'id': 1,
            'pictureurl': 'xxx',
            'name': 'xxx'
        }
    ]
}

/**
 * 3.园丁谱模块
 * 测试页面: http://bingexxx.net/test/powerGarden.html
 */

//获取所有教授列表    GET   http://bingexxx.net/api/powerGarden/
{
    code: 0,
    message: "成功",
    data: {
        1: [
                {
                    id: 1,
                    major: 1,
                    name: "彭小红"
                },
                {
                    id: 2,
                    major: 1,
                    name: "彭小红"
                }
        ],
        2: [
            {
                id: 3,
                major: 1,
                name: "彭小红"
            },
            {
                id: 4,
                major: 1,
                name: "彭小红"
            }
        ],
    }
}

//添加教授    Post         http://bingexxx.net/api/powerGarden/
{
    'major': '1',
        'name': 'xxx'
}

//删除教授    DELETE   http://bingexxx.net/api/powerGarden/{id}

/**
 * 4.站内索搜模块
 * 测试页面: http://bingexxx.net/test/powerGarden.html
 */

//索搜       Get          http://bingexxx.net/api/search
{
    "keyword": "xxx",
    ""
}