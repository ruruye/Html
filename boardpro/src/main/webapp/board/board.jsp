<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>
    
<!DOCTYPE html>
<html lang="kr">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

  <script src="../js/board.js"></script>
  <script src="../js/jquery.serializejson.min.js"></script>

<style>

body *{
	box-sizing: #EBFBFF;
}
p{
	border : 1px dotted lightgray;
	margin : 2px;
	padding : 5px;
}
.card-body{
	display : flex;
	flex-direction: column;
}
.p12{
	display: flex;
	flex-direction: row;
}
.p1{
	flex : 70%
}
.p2{
	flex : 30%;
	text-align: right;
}
input[name=reply]{
	height: 50px;
	vertical-align: top;
}
nav a{
/* 	display : none;  */
 	visibility: hidden; 
}
.reply-body{
	margin : 1px;
	padding : 3px;
	border : 1px dotted orange;
	background : #fad1f3;
}
#modiform{
	display : none;
}
</style>

<script>
mypath = '<%= request.getContextPath()%>';
console.log(mypath);

currentPage = 1;

$(function(){
	
	listPageServer(1);
	
	// 검색 이벤트
	$('#search').on('click', function(){
		// stype - writer, content, subject
		// sword - 입력한 값
		listPageServer(1);
	})
	
	// 다음버튼 이벤트
	$(document).on('click','#next', function(){
		// 다른 곳에 modiform이 열려 있는지 확인
			if( $('#modiform').css('display') != 'none'){
				// 열려있다
				replyReset(); // 이미열려 있는 modiform을 body 안으로 이동
			}
		currentPage = parseInt($('.pageno').last().text()) + 1;
		listPageServer(currentPage);
	})
	
	// 이전버튼 이벤트
	$(document).on('click','#prev', function(){
		// 다른 곳에 modiform이 열려 있는지 확인
			if( $('#modiform').css('display') != 'none'){
				// 열려있다
				replyReset(); // 이미열려 있는 modiform을 body 안으로 이동
			}
		currentPage = parseInt($('.pageno').first().text()) - 1;
		listPageServer(currentPage);
	})
	
	// 페이지번호 클릭 이벤트
	$(document).on('click', '.pageno', function(){
		// 다른 곳에 modiform이 열려 있는지 확인
			if( $('#modiform').css('display') != 'none'){
				// 열려있다
				replyReset(); // 이미열려 있는 modiform을 body 안으로 이동
			}
		currentPage = parseInt($(this).text().trim());
		listPageServer(currentPage);
	})
	
	//
	// 글쓰기 이벤트
	/*
 	$('#write').on('click', function(){
 		$('#wModal').modal('show');
 	})
 	*/
 	
 	//
 	// 글쓰기 모달창에서 입력후 전송 클릭 이벤트
 	$('#msend').on('click', function(){
 		
 		// 입력한 모든 값을 가져오기 
 		fdata = $('#mform').serializeJSON();
 		console.log(fdata);
 		
 		boardWriteServer();
 		
 		
 		// 모달에 입력한 내용 지우기
 		$('#wModal.txt').val("");
 		
 		// 모달창 닫기
 		$('#wModal').modal('hide');
 		
 	}) // click
 	
 	// 수정, 삭제, 등록, 댓글삭제, 댓글수정 이벤트
 	$(document).on('click','.action', function(){
 		vname = $(this).attr('name');
 		vidx = $(this).attr('idx');
 		
 		console.log(vname, vidx);
 		
 		if(vname == "delete"){
 			alert(vidx + "글을 삭제합니다");
 			
 			boardDeleteServer();
 			
 		}else if(vname == "modify"){
 			//alert(vidx + "글을 수정합니다");
 			
 			// modal창을 띄움
 			$('#uModal').modal('show');
 			
 			vparent = $(this).parents('.card');
 			
 			// 본문의 내용을 가져오기
 			wtitle = vparent.find('a').text().trim(); // 제목
 			wname = vparent.find('.wr').text(); // 작성자
 			wmail = vparent.find('.ma').text(); // 이메일
 			wcont = vparent.find('.p3').html(); // 내용
 			
 			console.log("title = " + wtitle);
 			
 			// 내용에 <br>태그를 \n으로 변경
 			wcont = wcont.replace(/<br>/g, "\n")
 			
 			// 모달창에 출력하기
 			$('#uform #unum').val(vidx); // hidden 글번호 출력
 			$('#uform #uwriter').val(wname);
 			$('#uform #umail').val(wmail);
 			$('#uform #usubject').val(wtitle);
 			$('#uform #ucontent').val(wcont);
 			
 		}else if(vname == "reply"){
 			alert(vidx + "글에 댓글을 답니다");
 			
 			vreply = $(this);
 			
 			vtval = $(this).prev().val();
 			
 			$(this).prev().val("");
 			
 			// 저장 하기 위해(renum, bonum(vidx), cont(vtval), name)
 			name1 = String.fromCharCode(parseInt(Math.random() * 26 + 65));
 			name2 = String.fromCharCode(parseInt(Math.random() * 26 + 97));
 			name3 = parseInt(Math.random() * 100 + 1);
 			name = name1 + name2 + name3;
 			
 			// 저장데이터를 객체로 생성
 			rdata = { };
 			rdata.bonum = vidx;
 			rdata.cont = vtval;
 			rdata.name = name;
 			
 			// 서버로 전송
 			replyInsertServer();
 			
 			
 		}else if(vname == "r_delete"){
 			alert(vidx + "번 댓글을 삭제합니다");
 			
 			vdelete = $(this);
 			replyDeleteServer();
 			
 		}else if(vname == "r_modify"){
 			alert(vidx + "번 댓글을 수정합니다");
 			
 			// 다른 곳에 modiform이 열려 있는지 확인
 			if( $('#modiform').css('display') != 'none'){
 				// 열려있다
 				replyReset(); // 이미열려 있는 modiform을 body 안으로 이동
 			}
 			
 			//$(this).parents('.p12').next();
 			vp3 = $(this).parents('.reply-body').find('.p3');
 			
 			// 원래 내용을 가져온다
 			rcont = $(vp3).html().trim(); //<br>태그가 포함
 			
 			// 원래 내용에서 <br>태그를 \n으로 변경
 			cont = rcont.replace(/<br>/g, "\n");
 			
 			// \n으로 변경한 내용을 수정창에 출력
 			$('#modiform textarea').val(cont);
 			
 			$(vp3).empty().append($('#modiform'));
 			
 			$('#modiform').show();
 			//$('#modiform').css('display', 'block');
 			
 		}
 		
 	}) // click
 	
 	replyReset = function(){
		
		// 현재 열려있는 modiform을 기준으로 p3을 찾는다 
		p3 = $('#modiform').parent();		
		
		// modiform을 body로 이동
		//$('#modiform').appendTo($('body'));
		$('body').append($('#modiform'));
		
		// modiform을 안보이게 설정
		$('#modiform').hide();
		//$('#modiform').css('display', 'none');
		
		// 원래내용을 p3으로 보이게 한다
		$(p3).html(rcont);
		
	}
 	
 	
 	
 	// 수정 모달창에서 데이터 수정 후 전송버튼 클릭
 	$('#usend').on('click', function(){
 		
 		// 수정해서 입력한 모든 값 가져오기
 		udata = $('#uform').serializeJSON()
 		console.log(udata);
 		
 		$('#uform.txt').val("");
 		$('#uModal').modal('hide');
 		
 		// 서버로 전송 - ajax수행
 		boardUpdateServer();
 		
 	})
 	
 	
 	// 제목을 클릭해서 조회수 증가
 	$(document).on('click', '.card a', function(){
 		
 		vreply = $(this);
 		
 		vidx = $(this).attr('idx');
 		//alert($(this).attr('aria-expanded'));
 		
 		hitattr = $(this).attr('aria-expanded');
		
 		vparents = $(this).parents(".card");
 		vhit = vparents.find(".hit").text();
 		console.log(vhit);
 		
 		if(hitattr == "true"){
 			//alert("조회수 증가");
 			
 			// 서버로 전송
 			boardHitUpdate();
 		}
 		
 		replyListServer();
 		
 	})
 	
 	
 	// modiform -> 댓글수정에서 취소버튼 클릭이벤트
 	$('#modireset').on('click', function(){
 		replyReset();
 	})
 	
 	// modiform -> 댓글수정에서 확인버튼 클릭이벤트
 	$('#modisend').on('click', function(){
 		
 		// 새롭게 입력한 값을 가져온다
 		newcont = $('#modiform textarea').val(); // 엔터기호포함
 		
 		// 엔터기호를 <br>로 변경
 		cont = newcont.replace(/\n/g, "<br>");
 		
 		// p3을 찾는다 - modiform을 기준으로 
 		p3 = $('#modiform').parent();
 		
 		// modiform을 body로 이동 - 안보이게 설정
 		$('#modiform').appendTo($('body'));
 		$('#modiform').hide();
 		
 		// 서버로 전송 - db수정 - newcont, vidx
 		reply = { };
 		reply.cont = newcont;
 		reply.renum = vidx;
 		
 		replyUpdateServer();
 		
 		// <br>로 변경한 새로 입력한 내용을 p3에 출력 - db수정 성공후
 		
 		
 	})
 	
 	
}) // $function
</script>
</head>

<body>
	
	<div id="modiform">
		<textarea rows="5" cols="30"></textarea>
		<input type="button" value="확인" id="modisend"> 
		<input type="button" value="취소" id="modireset"> 
	</div>


	<input data-bs-toggle="modal" data-bs-target="#wModal" type="button" value="글쓰기" id="write">
	<br><br>
	
	
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="javascript:void(0)">Logo</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">Link</a>
        </li>
      </ul>
      <form class="d-flex">
      
      <select class='form-select' id="stype">
      	<option value="">전체</option>
      	<option value="writer">작성자</option>
      	<option value="subject">제목</option>
      	<option value="content">내용</option>
      </select>
      
        <input class="form-control me-2" type="text" id="sword" placeholder="Search">
        <button class="btn btn-primary" type="button" id="search">Search</button>
      </form>
    </div>
  </div>
</nav>
	

	<div id="result">
	</div>
	<br><br>
	<div id="pageList">
	</div>
	
	
	<!-- 글쓰기 The Modal -->
	<div class="modal" id="wModal">
  	<div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      
        <form name="mfrom" id="mform">
        
           <input type="hidden" id="num" name="num" >
        
            <label>이름</label>
            <input type="text" class="txt" id="writer" name="writer"> <br> 
            
            <label>제목</label>
            <input type="text" class="txt" id="subject" name="subject"> <br> 
            
            <label>메일</label>
            <input type="text"  class="txt" id="mail" name="mail"> <br> 
            
            <label>비밀번호</label>
            <input type="password"  class="txt" id="password" name="password"> <br> 
            
            <label>내용</label>
            <br>
            <textarea rows="5" cols="40"  class="txt" id="content" name="content"></textarea>
            <br>
            <br>
            <input type="button" value="전송" id="msend">
        </form>
        
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
	





	
	<!-- 글수정 The Modal -->
	<div class="modal" id="uModal">
  	<div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      
        <form name="ufrom" id="uform">
        
           <input type="hidden" id="unum" name="num" >
        
            <label>이름</label>
            <input type="text" class="txt" id="uwriter" name="writer"> <br> 
            
            <label>제목</label>
            <input type="text" class="txt" id="usubject" name="subject"> <br> 
            
            <label>메일</label>
            <input type="text"  class="txt" id="umail" name="mail"> <br> 
            
            <label>비밀번호</label>
            <input type="password"  class="txt" id="upassword" name="password"> <br> 
            
            <label>내용</label>
            <br>
            <textarea rows="5" cols="40"  class="txt" id="ucontent" name="content"></textarea>
            <br>
            <br>
            <input type="button" value="전송" id="usend">
        </form>
        
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>	
	
</body>
</html>