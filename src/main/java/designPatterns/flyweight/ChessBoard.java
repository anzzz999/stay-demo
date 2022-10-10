package designPatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhanmingwei
 *
 * 享元模式的意图是复用对象，节省内存，前提是享元对象是不可变对象。
 *
 * 例子：
 * 利用工厂类来缓存 ChessPieceUnit 信息（也就是 id、text、color）。
 * 通过工厂类获取到的 ChessPieceUnit 就是享元。所有的 ChessBoard对象共享这 30 个 ChessPieceUnit 对象（因为象棋中只有 30 个棋子）。
 * 在使用享元模式之前，记录 1 万个棋局，我们要创建 30 万（30*1 万）个棋子的 ChessPieceUnit 对象。
 * 利用享元模式，我们只需要创建 30 个享元对象供所有棋局共享使用即可，大大节省了内存。
 *
 */
public class ChessBoard {
    private  Map<Integer, ChessPiece> chessPieces = new HashMap<>();
    public ChessBoard() {
        init();
    }

    private void init() {
        chessPieces.put(1, new ChessPiece(
                ChessPieceUnitFactory.getChessPiece(1), 0, 0));
        chessPieces.put(2, new ChessPiece(
                ChessPieceUnitFactory.getChessPiece(2), 1, 0));
        //...省略摆放其他棋子的代码...
    }

    public void move(int chessPieceId, int toPositionX, int toPositionY) {
        //...省略...
    }
}
