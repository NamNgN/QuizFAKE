<jsp:include page="template/header.jsp"></jsp:include>
    <script>
        var count = 4;
        function addQuestionInput() {
            var element = document.getElementById("question");
            var x = document.createElement("INPUT");
            x.setAttribute("type", "text");
            x.setAttribute("placeholder", "Enter term " + count);
            x.setAttribute("name", "term" + count);
            element.appendChild(x);
            var y = document.createElement("INPUT");
            y.setAttribute("type", "text");
            y.setAttribute("placeholder", "Definition " + count);
            y.setAttribute("name", "def" + count);
            element.appendChild(y);
            var z = document.createElement("BR");
            element.appendChild(z);
            count += 1;
        }
    </script>
    <main>
        <section>
            <form action="createCourse" method="POST"  style="text-align: center">
                <input type="text" placeholder="Title" id="name-6797" name="title" required="" style="padding: 20px">
                <input type="text" placeholder="Description" id="email-6797" name="description" required="" style="padding: 20px">
                <select name="visibleto" style="margin: 10px" >
                    <option value="0" selected="">Public</option>
                    <option value="1">Private</option>
                </select>
                <input type="submit" value="Create"><br/>
                <button onclick="addQuestionInput(); return false;" style="margin: 10px">More question</button><br/>
                <div id="question" style="margin-bottom:  100px;padding: 20px">
                    <input type="txt" name="term1" placeholder="Enter term 1" ><input type="txt" name="def1" placeholder="Definition 1"><br/>
                    <input type="txt" name="term2" placeholder="Enter term 2" ><input type="txt" name="def2" placeholder="Definition 2"><br/>
                    <input type="txt" name="term3" placeholder="Enter term 3" ><input type="txt" name="def3" placeholder="Definition 3"><br/>
                </div>
            </form>
        </section>
    </main>
<jsp:include page="template/footer.jsp" ></jsp:include>