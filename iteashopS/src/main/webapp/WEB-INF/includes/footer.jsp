<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    			  </div>
            
				<div id="sidebar">
					<table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    <c:if test='${ principal!=null }'>
                    Вы авторизировались как ${ principal.name }<br />
                    </c:if>
                    Товаров в Вашей корзине: <span id='totalAmount'>${cart!=null ? cart.productCount : 0 }</span>.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
					<ul>
						<li><a href="./products?category=1">Алкголь</a></li>
						<li><a href="./products?category=2">Наркотики</a></li>
						<li><a href="./products?category=3">Печеньки</a></li>
						<li><a href="./register">Регистрация</a></li>
						<li><a href="./login">Вход</a></li>
						<li><a href="./cart">Корзина</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) by Наумов Олександр</p>
</div>
<!-- end #footer -->
</body>
</html>
